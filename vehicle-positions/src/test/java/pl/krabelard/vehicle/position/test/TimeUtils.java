package pl.krabelard.vehicle.position.test;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils {

	public static final LocalDateTime LOCAL_DATE_TIME_NOW_MOCKED = LocalDateTime.of(
		2020,
		1,
		1,
		12,
		0
	);
}
