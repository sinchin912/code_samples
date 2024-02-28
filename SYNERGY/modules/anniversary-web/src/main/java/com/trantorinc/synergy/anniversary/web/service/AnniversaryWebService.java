package com.trantorinc.synergy.anniversary.web.service;

import com.trantorinc.synergy.anniversary.web.dto.AnniversaryDto;

import java.util.LinkedHashMap;
import java.util.List;

public interface AnniversaryWebService {
	LinkedHashMap<String, List<AnniversaryDto>> getBirthDays();
	LinkedHashMap<String, List<AnniversaryDto>> getAnniversaries();
    String getPhoto(String photoId);
}
