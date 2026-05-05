package com.lg.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Board {
	
	private int idx;
	@NonNull private String title;
	@NonNull private String writer;
	@NonNull private String filename;
	@NonNull private String content;
	private String b_date;
}
