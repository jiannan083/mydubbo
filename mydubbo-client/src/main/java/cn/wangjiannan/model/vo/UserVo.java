package cn.wangjiannan.model.vo;

import java.util.Date;
import java.util.List;

import cn.wangjiannan.model.Role;
import cn.wangjiannan.model.User;

/**
 * UserVo
 * 
 * @author wangjiannan
 * @date 2017年12月18日 下午1:34:38
 */
public class UserVo extends User {
	private static final long serialVersionUID = 1L;

	private List<Role> rolesList;

	private String organizationName;

	private String roleIds;

	private Date createdateStart;

	private Date createdateEnd;

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((createdateEnd == null) ? 0 : createdateEnd.hashCode());
		result = prime * result + ((createdateStart == null) ? 0 : createdateStart.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((roleIds == null) ? 0 : roleIds.hashCode());
		result = prime * result + ((rolesList == null) ? 0 : rolesList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVo other = (UserVo) obj;
		if (createdateEnd == null) {
			if (other.createdateEnd != null)
				return false;
		} else if (!createdateEnd.equals(other.createdateEnd))
			return false;
		if (createdateStart == null) {
			if (other.createdateStart != null)
				return false;
		} else if (!createdateStart.equals(other.createdateStart))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (roleIds == null) {
			if (other.roleIds != null)
				return false;
		} else if (!roleIds.equals(other.roleIds))
			return false;
		if (rolesList == null) {
			if (other.rolesList != null)
				return false;
		} else if (!rolesList.equals(other.rolesList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserVo [rolesList=" + rolesList + ", organizationName=" + organizationName + ", roleIds=" + roleIds + ", createdateStart=" + createdateStart
				+ ", createdateEnd=" + createdateEnd + "]";
	}

}