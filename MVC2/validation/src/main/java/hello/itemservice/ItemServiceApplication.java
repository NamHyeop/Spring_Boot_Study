package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args){
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/* Validator 글로벌 설정, 추가적으로 class에서 WebMvcConfigurer를 상속받아야함
	@Override
	public Validator getValidatr(){
		return new ItemValidator();
	}*/
}
