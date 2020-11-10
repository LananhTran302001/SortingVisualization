package element;

public class Vector2 {
    protected int x;
    protected int y;

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        if (other == null) {
            System.out.println("Gia tri vector2 = null.");
        } else {
            this.x = other.x;
            this.y = other.y;
        }
    }

    public void setVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 getVector2() {
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        if (other != null) {
            this.x += other.x;
            this.y += other.y;
        }
        return this;
    }

    public Vector2 subtract(Vector2 other) {
        if (other != null) {
            this.x -= other.x;
            this.y -= other.y;
        }
        return this;
    }

    public Vector2 multiple(int num) {
        this.x *= num;
        this.y *= num;
        return this;
    }

    public Vector2 multiple(Vector2 other) {
        if (other != null) {
            this.x *= other.x;
            this.y *= other.y;
        }
        return this;
    }

}
