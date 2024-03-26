package com.setebit.advocacia;

import com.setebit.advocacia.models.ItemModelo;
import com.setebit.advocacia.models.Modelo;
import com.setebit.advocacia.repository.ItemModeloRepository;
import com.setebit.advocacia.repository.ModeloRepository;
import com.setebit.advocacia.service.ModeloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdvocaciaApplicationTests {

	@Autowired
	ItemModeloRepository itemModeloRepository;

	@Autowired
	ModeloService modeloService;

	@Test
	void contextLoads() {
		Modelo modelo = Modelo.builder().id(1l).name("PETIÇÃO").build();

		ItemModelo  itemModelo = ItemModelo
				.builder()
				.modelo(modelo)
				.sequence(1)
				.description("The web is used by a wide variety of people, including those who have visual or motor impairments. A variety of assistive technologies are available that make it much easier for these groups to interact with web-based software applications. Also, designing an application to be more accessible generally improves the user experience for all users.")
				.build();
	}

}
