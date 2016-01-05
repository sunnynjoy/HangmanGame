package com.nexmo.hm.utils;

public enum GameStatusEnum {

	 WON(1), LOST(0), NEW(-1);

	    private int id; 
	    private GameStatusEnum(int id) {
	        this.setId(id);
	    }

	    public static GameStatusEnum fromId(int id) {
	            for (GameStatusEnum type : GameStatusEnum.values()) {
	                if (type.getId() == id) {
	                    return type;
	                }
	            }
	            return null;
	        }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	
}