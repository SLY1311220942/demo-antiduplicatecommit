package com.sly.demo.antiduplicatecommit.model;

import java.io.Serializable;

import com.sly.plugin.validate.constraints.Email;
import com.sly.plugin.validate.constraints.NotBlank;
import com.sly.plugin.validate.constraints.NumRange;
import com.sly.plugin.validate.constraints.Phone;

/**
 * 
 * @author sly
 * @time 2019年6月26日
 */
public class Business implements Serializable {

	private static final long serialVersionUID = -2948516389745592347L;

	@NotBlank(message = "Id不能为空", group = { "update" })
	private String Id;

	@NumRange(min = 0, max = 999, message = "Count范围为[0-999]", group = { "update", "add" })
	private Integer count;

	@Phone(message = "手机号格式不正确!", group = { "update", "add" })
	private String phone;

	@NotBlank(message = "邮箱不能为空!", group = { "update", "add" })
	@Email(message = "邮箱格式不正确!", group = { "update", "add" })
	private String email;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
