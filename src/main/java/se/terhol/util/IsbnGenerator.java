package se.terhol.util;

import java.util.Random;

public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generate() {
        return "13-5677-" + Math.abs(new Random().nextInt());
    }
}
