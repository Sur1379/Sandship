package org.example.sandship;

import org.example.sandship.service.DataService;
import org.example.sandship.service.materialType.impl.MaterialTypeServiceImpl;
import org.example.sandship.service.player.impl.PlayerServiceImpl;
import org.example.sandship.service.warehouse.impl.WarehouseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public DataService dataService() {
        return DataService.getInstance();
    }

    @Bean
    public PlayerServiceImpl playerServiceImpl(DataService dataService) {
        return new PlayerServiceImpl(dataService);
    }

    @Bean
    public WarehouseServiceImpl warehouseServiceImpl(DataService dataService) {
        return new WarehouseServiceImpl(dataService);
    }

    @Bean
    public MaterialTypeServiceImpl materialTypeServiceImpl(DataService dataService) {
        return new MaterialTypeServiceImpl(dataService);
    }
}
