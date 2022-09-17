// This class will help me to manage the distances and tags
// Just have it, any distance and the tag that corresponds to that distance
public class Distance{

    private Double distance;
    private Integer tag;

    // Simple constructor to assign values
    public Distance(double distance, int tag) {
        this.distance = distance;
        this.tag = tag;
    }

    // Returns the distance
    public Double getDistance() {
        return this.distance;
    }

    // Returns the tag
    public int getTag() {
        return tag;
    }

}
