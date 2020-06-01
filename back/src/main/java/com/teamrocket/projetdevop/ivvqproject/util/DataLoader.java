package com.teamrocket.projetdevop.ivvqproject.util;

import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

	private ProductService productService;

	private Product rocket1, rocket2, rocket3, rocket4, rocket5, rocket6;

	@Autowired
	public DataLoader(ProductService productService) {

		this.productService = productService;

	}

	@Override
	public void run(ApplicationArguments args) {

		initProducteur();

	}

	public void initRocket1() {
		rocket1 = new Product("B01", "Rocket1990", new BigDecimal(10000000), 100, "Amazing Rocket1990",
				"https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");
		productService.save(rocket1);

	}

	public void initRocket2() {
		rocket2 = new Product("B02", "Rocket2000", new BigDecimal(10000000), 100, "Amazing Rocket2000", ""
				+ "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");
		productService.save(rocket2);

	}

	public void initRocket3() {
		rocket3 = new Product("B03", "Rocket3000", new BigDecimal(10000000), 100, "Amazing Rocket3000", ""
				+ "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");

		productService.save(rocket3);

	}

	public void initRocket4() {
		rocket4 = new Product("B04", "Rocket4000", new BigDecimal(10000000), 100, "Amazing Rocket4000", ""
				+ "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");

		productService.save(rocket4);

	}
	public void initRocket5() {
		rocket5 = new Product("B05", "Rocket5000", new BigDecimal(10000000), 100, "Amazing Rocket5000",
				"https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");
		productService.save(rocket5);

	}

	public void initRocket6() {
		rocket6 = new Product("B06", "Rocket60000", new BigDecimal(10000000), 100, "Amazing Rocket6000", ""
				+ "https://img.pixers.pics/pho_wat(s3:700/FO/43/38/99/86/700_FO43389986_e1a030f717849f99319d1dec9c53186b.jpg,700,700,cms:2018/10/5bd1b6b8d04b8_220x50-watermark.png,over,480,650,jpg)/posters-flying-metal-rocket-isoated-vector-icon-3d.jpg.jpg");
		productService.save(rocket6);

	}

	public void initProducteur() {
		initRocket1();
		initRocket2();
		initRocket3();
		initRocket4();
		initRocket5();
		initRocket6();

	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		products.add(rocket1);
		products.add(rocket2);
		products.add(rocket3);
		products.add(rocket4);
		products.add(rocket5);
		products.add(rocket6);

		return products;
	}

	public Product getRocket1() {
		return rocket1;
	}
	public Product getRocket2() {
		return rocket2;
	}

	public Product getRocket3() {
		return rocket3;
	}

	public Product getRocket4() {
		return rocket4;
	}

	public Product getRocket5() {
		return rocket5;
	}

	public Product getRocket6() {
		return rocket6;
	}

}
