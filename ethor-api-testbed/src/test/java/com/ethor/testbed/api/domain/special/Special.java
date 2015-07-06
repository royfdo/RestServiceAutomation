package com.ethor.testbed.api.domain.special;

import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.ethor.testbed.api.domain.Entity;

/**
 * This class encapsulates attributes and methods of specials.
 * 
 * @author Roy Fernando.
 */
@XmlType(name="special")
public class Special extends Entity {

	private String name;
	private String description;
	private String longDescription;
	private SpecialType type;
	private Boolean visible;
	private Integer displayOrder;
	private String image;
	private Calendar effectiveDate;
	private Calendar expireDate;
	private Boolean exclusive;
	private Integer maxUsage;
	private Integer limitPerOrder;
	private List<Boolean> validDaysOfWeek;
	private String validFromTime;
	private String validToTime;
	private List<String> couponCodes;
	private List<SpecialItems> specialItems;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(final String longDescription) {
		this.longDescription = longDescription;
	}

	public SpecialType getType() {
		return type;
	}

	public void setType(final SpecialType type) {
		this.type = type;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(final Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public Calendar getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(final Calendar effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Calendar getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(final Calendar expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getMaxUsage() {
		return maxUsage;
	}

	public void setMaxUsage(final Integer maxUsage) {
		this.maxUsage = maxUsage;
	}

	public Integer getLimitPerOrder() {
		return limitPerOrder;
	}

	public void setLimitPerOrder(final Integer limitPerOrder) {
		this.limitPerOrder = limitPerOrder;
	}

	public List<Boolean> getValidDaysOfWeek() {
		return validDaysOfWeek;
	}

	public void setValidDaysOfWeek(List<Boolean> validDaysOfWeek) {
		this.validDaysOfWeek = validDaysOfWeek;
	}

	public String getValidFromTime() {
		return validFromTime;
	}

	public void setValidFromTime(final String validFromTime) {
		this.validFromTime = validFromTime;
	}

	public String getValidToTime() {
		return validToTime;
	}

	public void setValidToTime(final String validToTime) {
		this.validToTime = validToTime;
	}

	public List<String> getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(final List<String> couponCodes) {
		this.couponCodes = couponCodes;
	}

	public List<SpecialItems> getSpecialItems() {
		return specialItems;
	}

	public void setSpecialItems(List<SpecialItems> specialItems) {
		this.specialItems = specialItems;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(final Boolean visible) {
		this.visible = visible;
	}

	public Boolean getExclusive() {
		return exclusive;
	}

	public void setExclusive(final Boolean exclusive) {
		this.exclusive = exclusive;
	}

}
