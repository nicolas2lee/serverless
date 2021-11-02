package tao.berich.fund

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class FundApplication {

	fun main(args: Array<String>) {
		runApplication<FundApplication>( *args)
	}

	@Bean
	fun uppercase(): (String)->String {
		println("enter into uppercase function")
		return {
			it.uppercase();
		}
	}
}

