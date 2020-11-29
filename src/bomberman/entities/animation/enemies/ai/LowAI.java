package bomberman.entities.animation.enemies.ai;

public class LowAI extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
