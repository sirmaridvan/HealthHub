package halmob.healthhub.Models;

/**
 * Created by hakan on 11.11.2017.
 */

public class Food {
    private String foodName;
    private String foodType;
    private String portionSize;
    private String gram;
    private String protein;
    private String carbohydrate;
    private String fat;
    private String calorie;
    private String fiber;
    private String carbohydrateCount;
    private String glysemicIndex;


    public String getFoodName() { return foodName;}
    public void setFoodName(String newFoodName) {
        this.foodName = newFoodName;
    }

    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String newFoodType) {
        this.foodType = newFoodType;
    }

    public float getProtein() {
        float floatProtein = 0f;

        try {
            floatProtein = Float.parseFloat(protein.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatProtein;
    }
    public void setProtein(String newProtein) {
        this.protein = newProtein;
    }

    public float getCarbohydrate() {
        float floatCarbohydrate = 0f;

        try {
            floatCarbohydrate = Float.parseFloat(carbohydrate.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatCarbohydrate;
    }
    public void setCarbohydrate(String newCarbohydrate) {
        this.carbohydrate = newCarbohydrate;
    }

    public float getFat() {
        float floatFat = 0f;

        try {
            floatFat = Float.parseFloat(fat.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatFat;
    }
    public void setFat(String newFat) {
        this.fat = newFat;
    }

    public float getCalorie()
    {
        float floatCalorie = 0f;

        try {
            floatCalorie = Float.parseFloat(calorie.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatCalorie;
    }
    public void setCalorie(String newCalorie) {
        this.calorie = newCalorie;
    }

    public String getGram() {

        return gram;
    }
    public void setGram(String newGram) { this.gram = newGram; }

    public String getPortionSize() {
        return portionSize;
    }
    public void setPortionSize(String newPortionSize) {
        this.portionSize = newPortionSize;
    }

    public float getFiber() {
        float floatFiber = 0f;

        try {
            floatFiber = Float.parseFloat(fiber.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatFiber;
    }
    public void setFiber(String newFiber) {
        this.fiber = newFiber;
    }

    public float getCarbohydrateCount() {
        float floatCarbohydrateCount = 0f;

        try {
            floatCarbohydrateCount = Float.parseFloat(carbohydrateCount.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatCarbohydrateCount;
    }
    public void setCarbohydrateCount(String newCarbohydrateCount) { this.carbohydrateCount = newCarbohydrateCount; }

    public float getGlysemicIndex() {
        float floatGlysemicIndex = 0f;

        try {
            floatGlysemicIndex = Float.parseFloat(glysemicIndex.toString());
        } catch(NumberFormatException NFE) {
            System.out.println("Parsing int error: " + NFE);
        }
        return floatGlysemicIndex;
    }
    public void setGlysemicIndex(String newGlysemicIndex) {
        this.glysemicIndex = newGlysemicIndex;
    }

}
