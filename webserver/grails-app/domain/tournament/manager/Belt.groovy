package tournament.manager

class Belt {

    String description
    
    static constraints = {
        description(blank:false, size:2..30)
    }
    
    @Override
    public String toString() {
        return description
    }
	
	static belongsTo = [tournament:Tournament]
}
