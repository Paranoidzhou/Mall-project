package cn.tedu.store.bean;

import java.io.Serializable;

public class AddressParam implements Serializable{

	private static final long serialVersionUID = 8655325650818340696L;
	private Integer id;
	private String receiverName;
	private String receiverState;
	private String receiverCity;
	private String receiverDistrict;
	private String receiverAddress;
	private String receiverMobile;
	private String receiverPhone;
	private String receiverZip;
	private String addressName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressName == null) ? 0 : addressName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((receiverAddress == null) ? 0 : receiverAddress.hashCode());
		result = prime * result + ((receiverCity == null) ? 0 : receiverCity.hashCode());
		result = prime * result + ((receiverDistrict == null) ? 0 : receiverDistrict.hashCode());
		result = prime * result + ((receiverMobile == null) ? 0 : receiverMobile.hashCode());
		result = prime * result + ((receiverName == null) ? 0 : receiverName.hashCode());
		result = prime * result + ((receiverPhone == null) ? 0 : receiverPhone.hashCode());
		result = prime * result + ((receiverState == null) ? 0 : receiverState.hashCode());
		result = prime * result + ((receiverZip == null) ? 0 : receiverZip.hashCode());
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
		AddressParam other = (AddressParam) obj;
		if (addressName == null) {
			if (other.addressName != null)
				return false;
		} else if (!addressName.equals(other.addressName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (receiverAddress == null) {
			if (other.receiverAddress != null)
				return false;
		} else if (!receiverAddress.equals(other.receiverAddress))
			return false;
		if (receiverCity == null) {
			if (other.receiverCity != null)
				return false;
		} else if (!receiverCity.equals(other.receiverCity))
			return false;
		if (receiverDistrict == null) {
			if (other.receiverDistrict != null)
				return false;
		} else if (!receiverDistrict.equals(other.receiverDistrict))
			return false;
		if (receiverMobile == null) {
			if (other.receiverMobile != null)
				return false;
		} else if (!receiverMobile.equals(other.receiverMobile))
			return false;
		if (receiverName == null) {
			if (other.receiverName != null)
				return false;
		} else if (!receiverName.equals(other.receiverName))
			return false;
		if (receiverPhone == null) {
			if (other.receiverPhone != null)
				return false;
		} else if (!receiverPhone.equals(other.receiverPhone))
			return false;
		if (receiverState == null) {
			if (other.receiverState != null)
				return false;
		} else if (!receiverState.equals(other.receiverState))
			return false;
		if (receiverZip == null) {
			if (other.receiverZip != null)
				return false;
		} else if (!receiverZip.equals(other.receiverZip))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AddressParam [id=" + id + ", receiverName=" + receiverName + ", receiverState=" + receiverState
				+ ", receiverCity=" + receiverCity + ", receiverDistrict=" + receiverDistrict + ", receiverAddress="
				+ receiverAddress + ", receiverMobile=" + receiverMobile + ", receiverPhone=" + receiverPhone
				+ ", receiverZip=" + receiverZip + ", addressName=" + addressName + "]";
	}
	
}











