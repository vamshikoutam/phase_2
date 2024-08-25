package bean;

public class Batch {
    private int batchId;
    private String batchName;
    private String schedule;
    private String instructor;
    private int categoryId;

    public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	// Default constructor
    public Batch() {
    }

    // Parameterized constructor
    public Batch(int batchId, String batchName, String schedule, String instructor) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.schedule = schedule;
        this.instructor = instructor;
    }
    public Batch(String batchName, String schedule, String instructor) {
       this.batchName = batchName;
        this.schedule = schedule;
        this.instructor = instructor;
    }
    public Batch(String batchName, String schedule, String instructor, int categoryId) {
        this.batchName = batchName;
         this.schedule = schedule;
         this.instructor = instructor;
         this.categoryId = categoryId;
     }
    public Batch(String schedule, String instructor,int categoryId) {       
         this.schedule = schedule;
         this.instructor = instructor;
         this.categoryId = categoryId;
     }

    // Getter and Setter methods

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    // toString method to display batch information
    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", schedule='" + schedule + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}