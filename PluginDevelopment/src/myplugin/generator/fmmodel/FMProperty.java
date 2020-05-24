package myplugin.generator.fmmodel;

public class FMProperty extends FMElement {
	// Property type
	private String type;
	// Property visibility (public, private, protected, package)
	private String visibility;
	// Multiplicity (lower value)
	private Integer lower;
	// Multiplicity (upper value)
	private Integer upper;
	private boolean id;
	private Boolean association;
	private String aggregationKind;
	private Integer oppositeUpper;
	private Integer oppositeLower;
	private String fetchType = "";


	/**
	 * @ToDo: Add length, precision, unique... whatever is needed for ejb class
	 *        generation Also, provide these meta-attributes or tags in the modeling
	 *        languange metaclass or stereotype
	 */

	public FMProperty(String name, String type, String visibility, Integer lower, Integer upper, Boolean association,
			String aggregationKind, boolean id, Integer oppositeUpper, Integer oppositeLower) {
		super(name);
		this.type = type;
		this.visibility = visibility;
		this.lower = lower;
		this.upper = upper;
		this.association = association;
		this.aggregationKind = aggregationKind;
		this.id = id;
		this.oppositeLower = oppositeLower;
		this.oppositeUpper = oppositeUpper;
	}

	/*
	 * public FMProperty(String name, String type, String visibility, Integer lower,
	 * Integer upper) { super(name); this.type = type; this.visibility = visibility;
	 * this.lower = lower; this.upper = upper; }
	 */

	public String getType() {
		return type;
	}

	public Integer getOppositeUpper() {
		return oppositeUpper;
	}

	public void setOppositeUpper(Integer oppositeUpper) {
		this.oppositeUpper = oppositeUpper;
	}

	public Integer getOppositeLower() {
		return oppositeLower;
	}

	public void setOppositeLower(Integer oppositeLower) {
		this.oppositeLower = oppositeLower;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getUpper() {
		return upper;
	}

	public void setUpper(Integer upper) {
		this.upper = upper;
	}

	public Boolean getAssociation() {
		return association;
	}

	public void setAssociation(Boolean association) {
		this.association = association;
	}

	public String getAggregationKind() {
		return aggregationKind;
	}

	public void setAggregationKind(String aggregationKind) {
		this.aggregationKind = aggregationKind;
	}

	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public String getFetchType() {
		return fetchType;
	}

	public void setFetchType(String fetchType) {
		this.fetchType = fetchType;
	}
}