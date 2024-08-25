package bean;

public class Participant {
    private int participantId;
    private String participantName;
    private int age;
    private String gender;
    private int batchId;
    private String batchName;

    public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	// Default constructor
    public Participant() {
    }

    // Parameterized constructor
    public Participant(int participantId, String participantName, int age, String gender, int batchId) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.age = age;
        this.gender = gender;
        this.batchId = batchId;
        
    }
    
    // Parameterized constructor
    public Participant(int participantId, String participantName, int age, String gender, int batchId, String batchName) {
        this.participantId = participantId;
        this.participantName = participantName;
        this.age = age;
        this.gender = gender;
        this.batchId = batchId;
        this.batchName = batchName;
        
    }
    
    public Participant(String participantName, int age, String gender, int batchId,String batchName) {
        this.participantName = participantName;
        this.age = age;
        this.gender = gender;
        this.batchId = batchId;
        this.batchName = batchName;
    }
    public Participant(String participantName, int age, String gender, int batchId) {
        this.participantName = participantName;
        this.age = age;
        this.gender = gender;
        this.batchId = batchId;
        
    }


    // Getter and Setter methods

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    // toString method to display participant information
    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", participantName='" + participantName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", batchId=" + batchId +
                '}';
    }
}