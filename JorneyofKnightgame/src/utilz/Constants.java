package utilz;

public class Constants {
        public static class EnemyConstants{
        public static final int Boss = 0;
        public static final int Idle=0;
        public static final int Running=1;
        public static final int ATTACK=2;
        public static final int ATTACK_1=3;
        
        public static final int enemy_width = 351;
        public static final int enemy_height = 181;
        public static int GetSpriteAmount(int enemy_type, int enemy_state) {
                switch (enemy_type) {
                case Boss:
                    switch(enemy_state){
                        case ATTACK:
                            return 11;
                        case Running:
                            return 10;
                        case ATTACK_1:
                            return 8;
                        case Idle:
                            return 6;
                    }
            }
                return 0;
        }
               		public static int GetMaxHealth(int enemy_type) {
			switch (enemy_type) {
			case Boss:
				return 4000;
			default:
				return 1;
			}
		}

		public static int GetEnemyDmg(int enemy_type) {
			switch (enemy_type) {
			case Boss:
				return 50;
			default:
				return 0;
			}

		}
                
    }
        
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int RUNNINGRIGHT = 1;
		public static final int RUNNINGLEFT  = 2;
		public static final int RoLLRight = 3;
		public static final int RollLeft = 4;
		public static final int JUMP = 5;
		public static final int HIT = 7;
		public static final int DEATH = 7;
		public static final int ATTACK = 8;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
			case RollLeft:
                        case RoLLRight:
				return 12;
			case IDLE:
                        case RUNNINGLEFT:
                        case RUNNINGRIGHT:
                        case DEATH:
				return 10;
                        case JUMP:
                            return 7;
                        case ATTACK:
				return 4;
//                        case HIT:
			default:
				return 1;
			}
		}
	}

}
