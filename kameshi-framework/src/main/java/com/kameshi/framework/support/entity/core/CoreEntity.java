package com.kameshi.framework.support.entity.core;

import com.kameshi.framework.support.entity.ICoreEntity;

/**
 * 
 * Entidade generica da aplicacao que contem id do tipo Long
 * 
 */
public abstract class CoreEntity implements ICoreEntity {
	private static final long serialVersionUID = 8765860829620296489L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNew() {
		return getId() == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoreEntity other = (CoreEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
}
