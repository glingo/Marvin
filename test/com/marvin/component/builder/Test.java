package com.marvin.component.builder;

import static com.marvin.component.builder.Burger.*;
import static com.marvin.component.builder.Burger.BeefPatty.beef;
import static com.marvin.component.builder.Burger.Cheese.cheese;
import static com.marvin.component.builder.Burger.Tomato.tomato;

public class Test {
    
    public static void main(String[] args) {
        Burger lunch = burger()
            .with(beef()).andFree().topping(tomato());
 
//            Burger failure = burger()
//                .with(beef()).andFree().topping(cheese());


//        SteppedBuilder builder = new SteppedBuilder();
        
//        builder.build();
    }
}
