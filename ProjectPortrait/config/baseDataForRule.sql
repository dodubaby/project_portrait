INSERT INTO rule (
		regular,
		scan_file_suffix,
		key_left,
		key_right,
		rule_group,
		remark,
		creater,
		create_time
	)
	VALUES
		(
			'(?<=Integer.parseInt).+',
			'java',
			'Integer.parseInt',
			'',
			'error',
			'强转异常',
			'L.jinzhu',
			'system init'
		),
		(
			'(?<=Double.parseDouble).+',
			'java',
			'Double.parseDouble',
			'',
			'error',
			'强转异常',
			'L.jinzhu',
			'system init'
		),
		(
			'(?<=Float.parseFloat).+',
			'java',
			'Float.parseFloat',
			'',
			'error',
			'强转异常',
			'L.jinzhu',
			'system init'
		),
		(
			'(?<=Boolean.parseBoolean).+',
			'java',
			'Boolean.parseBoolean',
			'',
			'error',
			'强转异常',
			'L.jinzhu',
			'system init'
		),
		(
			'(?<=getCause).+',
			'java',
			'getCause',
			'',
			'error',
			'此方法会抛出异常',
			'L.jinzhu',
			'system init'
		),
		(
			'(?<=rectRoundCorner).+',
			'java',
			'rectRoundCorner',
			'',
			'warn',
			'需要用.asCircle、asPhotoCircle',
			'L.jinzhu',
		  'system init'
	)