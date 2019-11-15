package com.gpc.mapper;


import com.gpc.pojo.SearchRecords;
import com.gpc.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
	
	public List<String> getHotwords();

}