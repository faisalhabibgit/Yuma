package com.yuma.app.updater;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class YumaUserPOJO {

	private String id;
	private String firstName;
	private String lastName;
	private YumaGroupPOJO yumaGroupPOJO;
}
