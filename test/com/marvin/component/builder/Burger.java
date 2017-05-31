package com.marvin.component.builder;


public class Burger {
    
    private Patty patty;
    
    private Topping topping;

    public Burger() {
    }

    public Burger(Patty patty, Topping topping) {
        this.patty = patty;
        this.topping = topping;
    }

    public static BurgerBuilder burger() {
        return patty -> topping -> new Burger(patty, topping);
    }
    
    interface BurgerBuilder {
        ToppingBuilder with(Patty patty);
        default VegetarianBuilder vegetarian() {
            return patty -> topping -> new Burger(patty, topping);
        }
    }
    interface VegetarianBuilder {
        VegetarianToppingBuilder with(VegetarianPatty main);
    }
    interface VegetarianToppingBuilder {
        Burger and(VegetarianTopping topping);
    }
    interface ToppingBuilder {
        Burger and(Topping topping);
        default FreeToppingBuilder andFree() {
            return topping -> and(topping);
        }
    }
    interface FreeToppingBuilder {
        Burger topping(FreeTopping topping);
    }

    interface Omnivore extends Vegetarian {}
    interface Vegetarian extends Vegan {}
    interface Vegan extends DietaryChoice {}
    interface DietaryChoice {}

    interface Topping {}
    interface VegetarianTopping extends Vegetarian, Topping {}
    interface FreeTopping extends Topping {}
    interface Bacon extends Topping {
        public static Bacon bacon() { return null; }
    }
    interface Tomato extends VegetarianTopping, FreeTopping {
        public static Tomato tomato() { return null; }
    }
    interface Cheese extends VegetarianTopping {
        public static Cheese cheese() { return null; }
    }

    interface Patty {}
    interface BeefPatty extends Patty {
        public static BeefPatty beef() { return null;}
    }
    interface VegetarianPatty extends Patty, Vegetarian {}
    interface Tofu extends VegetarianPatty {
        public static Tofu tofu() { return null; }
    }
    interface Mushroom extends VegetarianPatty {
        public static Mushroom mushroom() { return null; }
    }
}
