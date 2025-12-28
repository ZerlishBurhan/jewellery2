package com.ecommerce.jewelleryMart.strategy;

public class SortStrategyFactory {

    public static SortStrategy getStrategy(String sort) {

        if (sort == null) return null;

        switch (sort) {
            case "priceLowToHigh":
                return new PriceLowToHighStrategy();

            case "priceHighToLow":
                return new PriceHighToLowStrategy();

            case "nameAsc":
                return new NameAscStrategy();

            case "nameDesc":
                return new NameDescStrategy();

            default:
                return null;
        }
    }
}
