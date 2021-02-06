package com.restaurants.restaurants.controller;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restaurants.restaurants.domain.Restaurant;

public interface RestaurantControllerRepo extends CrudRepository<Restaurant, Long> {

}
