package com.yotech.demorest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("aliens")
public class AlienResource {
AlienRepository mAlienRepository= new AlienRepository();
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public List<Alien> getAliens() {
	System.out.println("AlienResource Called");
	

	
	return mAlienRepository.getAliens();
}	

	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public Alien getAlien(@PathParam("id") int id) {
	System.out.println("getAlien Called");
	
	return mAlienRepository.getAlien(id);
}	

	@POST
	@Path("createAlien")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a1) {
		System.out.println(a1);
		mAlienRepository.create(a1);
		return a1;
	}
	@PUT
	@Path("updateAlien")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a1) {
		System.out.println(a1);
		if (mAlienRepository.getAlien(a1.getId())==null) {
			mAlienRepository.create(a1);
			System.out.println(a1+"if");
		}
		else {
		mAlienRepository.update(a1);
		System.out.println(a1+"else");

		}
		return a1;
	}


	@DELETE
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public Alien deleteAlien(@PathParam("id") int id) {
		Alien a= mAlienRepository.getAlien(id);
	System.out.println("deleteAlien Called");
	if(a.getId()!=0)
		mAlienRepository.delete(id);
	return mAlienRepository.getAlien(id);
}	
}

