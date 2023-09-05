package fi.projecttest.testProject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.projecttest.testProject.domain.ProductInfo;
import fi.projecttest.testProject.domain.ProductInfoRepository;
import fi.projecttest.testProject.domain.ProductPrice;
import fi.projecttest.testProject.domain.ProductPriceID;
import fi.projecttest.testProject.domain.ProductPriceRepository;
import fi.projecttest.testProject.domain.SupermarketRepository;
import fi.projecttest.testProject.domain.Supermarket;


@SpringBootApplication
public class TestProjectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TestProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(ProductPriceRepository prep, SupermarketRepository srep, ProductInfoRepository pirep) {
		return (args) -> {
			log.info("save a couple of shops");
			
			//creating and saving Shops
			srep.save(new Supermarket("Prisma", "S-Group", "Pekankatu 5", "Olaf Uoli", "12345"));
			srep.save(new Supermarket("Lidl", "Lidl", "Pekankatu 5", "Krista Pipponen", "67890"));	

			//ShopIDs
			Long lidlID = new Long(srep.findBySName("Lidl").get(0).getsID());
			Long prismaID = new Long(srep.findBySName("Prisma").get(0).getsID());
			
			//Products
			ProductInfo tunaRainbow = new ProductInfo("tuna", "Rainbow");
			ProductInfo tunaLidl = new ProductInfo("tuna", "NIXE");
			ProductInfo milkKoti = new ProductInfo("milk", "Kotimaista");
			ProductInfo milkMilbona = new ProductInfo("milk", "Milbona");
			ProductInfo milkValio = new ProductInfo("milk", "Valio");
			ProductInfo tomatoFin = new ProductInfo("tomato", "Finnish");
			
			//Saving products
			pirep.save(tunaRainbow);
			pirep.save(tunaLidl);
			pirep.save(milkKoti);
			pirep.save(milkMilbona);
			pirep.save(milkValio);
			pirep.save(tomatoFin);
			
			//ID of products
			Long tomatoID = pirep.findByPName("tomato").get(0).getpID();
			Long milkValioID = pirep.findByPNameAndPBrand("milk", "Valio").get(0).getpID();
			Long milkMilboanID = pirep.findByPNameAndPBrand("milk", "Milbona").get(0).getpID();
			Long milkKotiID = pirep.findByPNameAndPBrand("milk", "Kotimaista").get(0).getpID();
			Long tunaLidlID = pirep.findByPNameAndPBrand("tuna", "NIXE").get(0).getpID();
			Long tunaPrismaID = pirep.findByPNameAndPBrand("tuna", "Rainbow").get(0).getpID();
			
			log.info(pirep.findByPNameAndPBrand("milk", "Valio").get(0).toString());
			log.info(Long.toString(milkValioID));
			
			//Defining ProductPriceID
			ProductPriceID tomatoL = new ProductPriceID(lidlID, tomatoID);
			ProductPriceID tomatoP = new ProductPriceID(prismaID, tomatoID);
			ProductPriceID tunaL = new ProductPriceID(lidlID, tunaLidlID);
			ProductPriceID tunaP = new ProductPriceID(prismaID, tunaPrismaID);
			ProductPriceID milkL = new ProductPriceID(lidlID, milkMilboanID);
			ProductPriceID milkP = new ProductPriceID(prismaID, milkKotiID);
			ProductPriceID valio = new ProductPriceID(prismaID, milkValioID);
			
			//Defining prices
			prep.save(new ProductPrice(valio, srep.findBySName("Prisma").get(0), pirep.findByPNameAndPBrand("milk", "Valio").get(0), 1.05));
			prep.save(new ProductPrice(milkP, srep.findBySName("Prisma").get(0), pirep.findByPNameAndPBrand("milk", "Kotimaista").get(0), 0.89));
			prep.save(new ProductPrice(tunaP, srep.findBySName("Prisma").get(0), pirep.findByPNameAndPBrand("tuna", "Rainbow").get(0), 1.59));
			prep.save(new ProductPrice(tomatoP, srep.findBySName("Prisma").get(0), pirep.findByPName("tomato").get(0), 3.99));
			prep.save(new ProductPrice(tomatoL, srep.findBySName("Lidl").get(0), pirep.findByPName("tomato").get(0), 2.99));
			prep.save(new ProductPrice(tunaL, srep.findBySName("Lidl").get(0), pirep.findByPNameAndPBrand("tuna", "NIXE").get(0), 1.09));
			prep.save(new ProductPrice(milkL, srep.findBySName("Lidl").get(0), pirep.findByPNameAndPBrand("milk", "Milbona").get(0), 0.89));
			
			log.info(srep.findBySName("Prisma").get(0).getsID().toString());
			List<ProductPrice> items = prep.findBySID(srep.findById(new Long(1)).get());
			for (int i=0; i<items.size(); i++) {
				log.info(items.get(i).toString());
			}
			
			log.info(prep.findBySIDAndPID(srep.findBySName("Prisma").get(0), pirep.findByPNameAndPBrand("milk", "Valio").get(0)).get(0).toString());
			
			log.info(pirep.findById(new Long(8)).get().getpName());
			ProductInfo tamato = pirep.findById(new Long(8)).get();
			tamato.setpName("Tamato");
			pirep.save(tamato);
			log.info(pirep.findById(new Long(8)).get().getpName());
			
			ProductPrice priceTomato = prep.findBySIDAndPID(srep.findBySName("Prisma").get(0), pirep.findByPNameAndPBrand("Tamato", "Finnish").get(0)).get(0);
			
			log.info(priceTomato.toString());
			
			priceTomato.getpID().setpName("TomatoT");
			priceTomato.setPrice(10.0);
			log.info(priceTomato.toString());
			prep.save(priceTomato);

		};
	}
	
}
