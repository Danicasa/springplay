package com.danicasa.microservices.webflux.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.danicasa.microservices.webflux.models.document.Project;
import com.danicasa.microservices.webflux.service.ProjectService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProjectHandler {

	@Autowired
	private ProjectService service;
	
	@Value("${config.base.endpoint}")
	private String url;
	
	@Autowired
	private Validator validator;
	
	public Mono<ServerResponse> list(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll(), Project.class);
	}
	
	public Mono<ServerResponse> view(ServerRequest request){
		
		String id = request.pathVariable("id");
		return service.findById(Long.parseLong(id)).flatMap( p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> create(ServerRequest request){
		Mono<Project> producto = request.bodyToMono(Project.class);
		
		return producto.flatMap(p -> {
			
			Errors errors = new BeanPropertyBindingResult(p, Project.class.getName());
			validator.validate(p, errors);
			
			if(errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "The field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList()
						.flatMap(list -> ServerResponse.badRequest().body(fromObject(list)));
			} else {
				return service.save(p).flatMap(pdb -> ServerResponse
						.created(URI.create(url + pdb.getId()))
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromObject(pdb)));
			}
			
		});
	}
	
	public Mono<ServerResponse> edit(ServerRequest request){
		Mono<Project> project = request.bodyToMono(Project.class);
		String id = request.pathVariable("id");

		Mono<Project> projectDb = service.findById(Long.parseLong(id));
		
		return projectDb.zipWith(project, (db, req) ->{
			db.setName(req.getName());
			db.setDescription(req.getDescription());
			return db;
		}).flatMap(p -> ServerResponse.created(URI.create(url.concat(id)))
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.save(p), Project.class))
		.switchIfEmpty(ServerResponse.notFound().build());
		
	}
	
	public Mono<ServerResponse> delete(ServerRequest request){
		String id = request.pathVariable("id");
		
		Mono<Project> projectDb = service.findById(Long.parseLong(id));
		
		return projectDb.flatMap(p-> service.delete(p).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
		
	}

}
