package cn.tedu.store.bean;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable{
	
	private static final long serialVersionUID = -3409939019877574898L;
	private Integer id;
	private Integer uid;
	private String recvUsername;
	private String recvProvinceCode;
	private String recvCityCode;
	private String recvAreaCode;
	private String recvAddress;
	private String recvDistrict;
	private String recvPhone;
	private String recvTel;
	private String recvZip;
	private String recvTag;
	private Integer isDefault;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getRecvUsername() {
		return recvUsername;
	}
	public void setRecvUsername(String recvUsername) {
		this.recvUsername = recvUsername;
	}
	public String getRecvProvinceCode() {
		return recvProvinceCode;
	}
	public void setRecvProvinceCode(String recvProvinceCode) {
		this.recvProvinceCode = recvProvinceCode;
	}
	public String getRecvCityCode() {
		return recvCityCode;
	}
	public void setRecvCityCode(String recvCityCode) {
		this.recvCityCode = recvCityCode;
	}
	public String getRecvAreaCode() {
		return recvAreaCode;
	}
	public void setRecvAreaCode(String recvAreaCode) {
		this.recvAreaCode = recvAreaCode;
	}
	public String getRecvAddress() {
		return recvAddress;
	}
	public void setRecvAddress(String recvAddress) {
		this.recvAddress = recvAddress;
	}
	public String getRecvDistrict() {
		return recvDistrict;
	}
	public void setRecvDistrict(String recvDistrict) {
		this.recvDistrict = recvDistrict;
	}
	public String getRecvPhone() {
		return recvPhone;
	}
	public void setRecvPhone(String recvPhone) {
		this.recvPhone = recvPhone;
	}
	public String getRecvTel() {
		return recvTel;
	}
	public void setRecvTel(String recvTel) {
		this.recvTel = recvTel;
	}
	public String getRecvZip() {
		return recvZip;
	}
	public void setRecvZip(String recvZip) {
		this.recvZip = recvZip;
	}
	public String getRecvTag() {
		return recvTag;
	}
	public void setRecvTag(String recvTag) {
		this.recvTag = recvTag;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((createdUser == null) ? 0 : createdUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isDefault == null) ? 0 : isDefault.hashCode());
		result = prime * result + ((modifiedTime == null) ? 0 : modifiedTime.hashCode());
		result = prime * result + ((modifiedUser == null) ? 0 : modifiedUser.hashCode());
		result = prime * result + ((recvAddress == null) ? 0 : recvAddress.hashCode());
		result = prime * result + ((recvAreaCode == null) ? 0 : recvAreaCode.hashCode());
		result = prime * result + ((recvCityCode == null) ? 0 : recvCityCode.hashCode());
		result = prime * result + ((recvDistrict == null) ? 0 : recvDistrict.hashCode());
		result = prime * result + ((recvPhone == null) ? 0 : recvPhone.hashCode());
		result = prime * result + ((recvProvinceCode == null) ? 0 : recvProvinceCode.hashCode());
		result = prime * result + ((recvTag == null) ? 0 : recvTag.hashCode());
		result = prime * result + ((recvTel == null) ? 0 : recvTel.hashCode());
		result = prime * result + ((recvUsername == null) ? 0 : recvUsername.hashCode());
		result = prime * result + ((recvZip == null) ? 0 : recvZip.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (createdUser == null) {
			if (other.createdUser != null)
				return false;
		} else if (!createdUser.equals(other.createdUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDefault == null) {
			if (other.isDefault != null)
				return false;
		} else if (!isDefault.equals(other.isDefault))
			return false;
		if (modifiedTime == null) {
			if (other.modifiedTime != null)
				return false;
		} else if (!modifiedTime.equals(other.modifiedTime))
			return false;
		if (modifiedUser == null) {
			if (other.modifiedUser != null)
				return false;
		} else if (!modifiedUser.equals(other.modifiedUser))
			return false;
		if (recvAddress == null) {
			if (other.recvAddress != null)
				return false;
		} else if (!recvAddress.equals(other.recvAddress))
			return false;
		if (recvAreaCode == null) {
			if (other.recvAreaCode != null)
				return false;
		} else if (!recvAreaCode.equals(other.recvAreaCode))
			return false;
		if (recvCityCode == null) {
			if (other.recvCityCode != null)
				return false;
		} else if (!recvCityCode.equals(other.recvCityCode))
			return false;
		if (recvDistrict == null) {
			if (other.recvDistrict != null)
				return false;
		} else if (!recvDistrict.equals(other.recvDistrict))
			return false;
		if (recvPhone == null) {
			if (other.recvPhone != null)
				return false;
		} else if (!recvPhone.equals(other.recvPhone))
			return false;
		if (recvProvinceCode == null) {
			if (other.recvProvinceCode != null)
				return false;
		} else if (!recvProvinceCode.equals(other.recvProvinceCode))
			return false;
		if (recvTag == null) {
			if (other.recvTag != null)
				return false;
		} else if (!recvTag.equals(other.recvTag))
			return false;
		if (recvTel == null) {
			if (other.recvTel != null)
				return false;
		} else if (!recvTel.equals(other.recvTel))
			return false;
		if (recvUsername == null) {
			if (other.recvUsername != null)
				return false;
		} else if (!recvUsername.equals(other.recvUsername))
			return false;
		if (recvZip == null) {
			if (other.recvZip != null)
				return false;
		} else if (!recvZip.equals(other.recvZip))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", uid=" + uid + ", recvUsername=" + recvUsername + ", recvProvinceCode="
				+ recvProvinceCode + ", recvCityCode=" + recvCityCode + ", recvAreaCode=" + recvAreaCode
				+ ", recvAddress=" + recvAddress + ", recvDistrict=" + recvDistrict + ", recvPhone=" + recvPhone
				+ ", recvTel=" + recvTel + ", recvZip=" + recvZip + ", recvTag=" + recvTag + ", isDefault=" + isDefault
				+ ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
}
