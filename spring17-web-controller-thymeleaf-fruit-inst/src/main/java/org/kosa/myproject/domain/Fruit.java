package org.kosa.myproject.domain;

public class Fruit {
    private String name;     // 과일 이름
    private String color;    // 색상
    private int price;       // 가격
    private String origin;   // 원산지
    
    // 기본 생성자
    public Fruit() {}
    
    // 전체 매개변수 생성자
    public Fruit(String name, String color, int price, String origin) {
        this.name = name;
        this.color = color;
        this.price = price;
        this.origin = origin;
    }
    
    // getter/setter 메소드들
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    @Override
    public String toString() {
        return String.format("Fruit{name='%s', color='%s', price=%d, origin='%s'}", 
                           name, color, price, origin);
    }
}