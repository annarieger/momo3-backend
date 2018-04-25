/**
 *
 */
package de.terrestris.momo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.terrestris.momo.model.tree.LayerTreeLeaf;
import de.terrestris.momo.util.serializer.MomoLayerSerializer;
import de.terrestris.shogun2.model.User;
import de.terrestris.shogun2.model.layer.Layer;

/**
 * @author Nils Bühner
 *
 */
@Entity
@Table
@JsonSerialize(using=MomoLayerSerializer.class)
public class MomoLayer extends Layer {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Boolean spatiallyRestricted;

	/**
	 *
	 */
	private Boolean hoverable;

	/**
	 *
	 */
	private Boolean chartable;

	/**
	 *
	 */
	private String dataType;

	/**
	 *
	 */
	private String metadataIdentifier;

	/**
	 * If this value IS NOT(null OR empty), it will be used as a fix legend URL
	 * (instead of the GetLegendGraphic)
	 */
	private String fixLegendUrl = null;


	/**
	 *
	 */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="LAYER_ID")
	private List<LayerTreeLeaf> layerTreeLeaves;

	/**
	 *
	 */
	@ManyToOne
	private User owner;

	/**
	 *
	 */
	public MomoLayer() {
	}

	/**
	 * @return the spatiallyRestricted
	 */
	public Boolean getSpatiallyRestricted() {
		return spatiallyRestricted;
	}

	/**
	 * @param spatiallyRestricted the spatiallyRestricted to set
	 */
	public void setSpatiallyRestricted(Boolean spatiallyRestricted) {
		this.spatiallyRestricted = spatiallyRestricted;
	}

	/**
	 * @return the hoverable
	 */
	public Boolean getHoverable() {
		return hoverable;
	}

	/**
	 * @param hoverable the hoverable to set
	 */
	public void setHoverable(Boolean hoverable) {
		this.hoverable = hoverable;
	}

	/**
	 * @return the chartable
	 */
	public Boolean getChartable() {
		return chartable;
	}

	/**
	 * @param chartable the chartable to set
	 */
	public void setChartable(Boolean chartable) {
		this.chartable = chartable;
	}

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the metadataIdentifier
	 */
	public String getMetadataIdentifier() {
		return metadataIdentifier;
	}

	/**
	 * @param metadataIdentifier the metadataIdentifier to set
	 */
	public void setMetadataIdentifier(String metadataIdentifier) {
		this.metadataIdentifier = metadataIdentifier;
	}

	/**
	 * @return the fixLegendUrl
	 */
	public String getFixLegendUrl() {
		return fixLegendUrl;
	}


	/**
	 * @param fixLegendUrl the fixLegendUrl to set
	 */
	public void setFixLegendUrl(String fixLegendUrl) {
		this.fixLegendUrl = fixLegendUrl;
	}


	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	@Override
	public int hashCode() {
		// two randomly chosen prime numbers
		return new HashCodeBuilder(29, 11).
				appendSuper(super.hashCode()).
				append(getSpatiallyRestricted()).
				append(getHoverable()).
				append(getChartable()).
				append(getDataType()).
				append(getFixLegendUrl()).
				toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 *
	 *      According to
	 *      http://stackoverflow.com/questions/27581/overriding-equals
	 *      -and-hashcode-in-java it is recommended only to use getter-methods
	 *      when using ORM like Hibernate
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MomoLayer))
			return false;
		MomoLayer other = (MomoLayer) obj;

		return new EqualsBuilder().
				appendSuper(super.equals(other)).
				append(getSpatiallyRestricted(), other.getSpatiallyRestricted()).
				append(getHoverable(), other.getHoverable()).
				append(getChartable(), other.getChartable()).
				append(getDataType(), other.getDataType()).
				append(getFixLegendUrl(), other.getFixLegendUrl()).
				isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
