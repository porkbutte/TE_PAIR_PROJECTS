BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, venue_type, landmarks, itinerary, itinerary_landmark, reviews;
DROP SEQUENCE IF EXISTS seq_user_id, seq_itinerary_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE venue_type (
	venue_type_id serial not null,
	venue_type varchar (50) not null,
	constraint pk_venue_type_id primary key (venue_type_id)
);

CREATE TABLE landmarks (
	landmark_id serial not null,
	landmark_name varchar (50) not null,
	description varchar (500) not null,
	hours_of_operation varchar (100),
	longitude DECIMAL(9,6) not null,
	latitude DECIMAL (8,6) not null,
	venue_type_id int,
	family_friendly boolean not null,
	approved boolean,
	images varchar (500),
	constraint PK_landmark primary key (landmark_id),
	constraint fk_landmarks_venue_type_id foreign key (venue_type_id) references venue_type(venue_type_id)
);


CREATE SEQUENCE seq_itinerary_id
INCREMENT BY 1
START WITH 1000
NO MAXVALUE;

CREATE TABLE itinerary (
	itinerary_id int DEFAULT NEXTVAL('seq_itinerary_id') NOT NULL,
	itinerary_name varchar (50) not null,
	user_id int not null UNIQUE,
	starting_longitude DECIMAL (9,6) not null,
	starting_latitude DECIMAL (8,6) not null,
	itinerary_date varchar (100) not null,
	constraint PK_itinerary primary key (itinerary_id),
	constraint fk_itinerary_user_id foreign key (user_id) references users(user_id)
);

CREATE TABLE itinerary_landmark (
	itinerary_id int not null,
	landmark_id int not null,
	CONSTRAINT PK_itinerary_landmark PRIMARY KEY (itinerary_id, landmark_id),
	CONSTRAINT FK_itinerary_landmark_itinerary FOREIGN KEY (itinerary_id) REFERENCES itinerary (itinerary_id),
	CONSTRAINT FK_itinerary_landmark_landmark FOREIGN KEY (landmark_id) REFERENCES landmarks (landmark_id)
);


CREATE TABLE reviews (
	review_id serial not null,
	landmark_id int not null,
	name varchar (20) not null,
	description varchar (500) not null,
	rating boolean not null,
	user_id int not null,
	constraint pk_reviews primary key (review_id),
	constraint fk_reviews_landmark_id foreign key (landmark_id) references landmarks(landmark_id),
	constraint fk_reviews_user_id foreign key (user_id) references users(user_id)
);

insert into venue_type (venue_type)
values ('Entertainment');

insert into venue_type (venue_type)
values ('Restaurant');

insert into venue_type (venue_type)
values ('Brewery');

insert into venue_type (venue_type)
values ('Bar');

insert into venue_type (venue_type)
values ('Shopping');

insert into venue_type (venue_type)
values ('Park');

insert into venue_type (venue_type)
values ('Historic');

insert into venue_type (venue_type)
values ('Museum');

insert into venue_type (venue_type)
values ('Attraction');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ('Lotus Food Company', 'Asian Market', 'MTWThFSS 10AM-6PM', 40.44982322782111, -79.98513413825958, 5, true, true, 'https://media-cdn.tripadvisor.com/media/photo-p/1d/36/11/a7/lotus-foods-in-the-strip.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Wholey''s', 'Long-running store featuring seafood, poultry & meat products, plus produce & other grocery items.', 'M-Sat:8AM-5PM Sun:9AM-5PM', 40.45017429480229, -79.98498393455023, 5, true, true, 'https://goodfoodpittsburgh.com/wp-content/uploads/2020/09/wholey1.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'The Benedum Center for the Performing Arts', 'Theater and concert hall', 'Event Schedule', 40.44325060446457, -79.99932471869673, 1, true, true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Benedum_Center_-_IMG_7632.JPG/1024px-Benedum_Center_-_IMG_7632.JPG');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'The Warren Bar & Burrow', 'Neighborhood watering hole preparing house concoctions & bar bites curated by its sister eatery.', 'M-F: 5PM-2AM SS: 11AM-2AM', 40.44325060446457, -79.99932471869673, 4, false, true, 'https://s3-media0.fl.yelpcdn.com/bphoto/vliqU38ohcrww7rJYjLmQA/o.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Pittsburgh Opera', 'Once George Westinghouse"s brake factory, this brick-lined opera house features a lineup of shows.', 'M-F: 9AM-5PM', 40.45339201411948, -79.97925058800527, 1, true, true, 'https://www.pittsburghopera.org/uploads/MIssionHist_714_x_294.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Gaucho Parrilla Argentina', 'Cheery Argentinian haunt with patio seating preparing wood-fire-grilled meats, plus salads & sides.', 'T-Sat: 4PM-10pm', 40.44299467488305, -80.00179809964189, 2, true, true, 'https://dinnerdivasblog.files.wordpress.com/2022/01/0d866b2e-6ce8-4f43-a1af-3e0c868252f4-edited-1.jpeg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Point Park', 'Pennsylvania state park on 36 acres in Downtown Pittsburgh, Allegheny County, Pennsylvania, USA, at the confluence of the Allegheny and Monongahela rivers, forming the Ohio River.
', 'M-Sun: 7AM-11PM', 40.4419165631027, -80.01273024633825, 6, true, true, 'https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/point-state-park-fountain-bill-cobb.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'William Penn Hotel', 'Stately quarters in a historic hotel with a chic restaurant, a tearoom & a grand ballroom.', 'M-Sun: 12AM-12PM', 40.44083155074114, -79.99609406102404, 7, true, true, 'https://www.omnihotels.com/-/media/images/hotels/pitdtn/digex/carousel/pitdtn_1_2880x1870.jpeg?mw=1980&hash=E3C2AF712E58E2CF2A387D4EFDB570B5142DE903');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Biergarten Restaurant at the Kimpton Hotel Monaco', 'Seasonal rooftop hangout at Hotel Monaco with German-influenced grub, European beers & pub games.', 'Th-Sat: 5PM-11PM', 40.44159757548415, -79.99562148800574, 4, true, true, 'https://www.therooftopguide.com/rooftop-bars-in-pittsburgh/Bilder/biergarten-hotel-monaco-1.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'PPG Arena', 'Multi-purpose indoor arena in Pittsburgh, that serves as the home of the Pittsburgh Penguins of the National Hockey League', 'Event Schedule', 40.44026821793814, -79.98881098058717, 1, true, true, 'https://scontent.fagc1-1.fna.fbcdn.net/v/t1.6435-9/92482439_10158191553817618_7591251302932283392_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=5YBSLUtaOgYAX--CwO1&_nc_ht=scontent.fagc1-1.fna&oh=00_AT8X0htpyBdrWaVXOge6c52MbIyHHrFH4R8Rg4GZkBKHqw&oe=628298F9');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'PNC Park', 'Major League Baseball stadium located on the North Shore', 'Event Schedule', 40.447571189768425, -80.00517553404204, 1, true, true, 'https://upload.wikimedia.org/wikipedia/commons/0/0f/PNC-Park-exterior-in-evening-May-2020.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Children"s Museum', 'Hands-on interactive museum', 'M-Sun: 10AM-5PM', 40.45302305119115, -80.00564332662324, 8, true, true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Children%27s_Museum_of_Pittsburgh.jpg/1024px-Children%27s_Museum_of_Pittsburgh.jpg');

insert into landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
values ( 'Stage AE', 'A multi-purpose entertainment complex.', 'Event Schedule', 40.45727411736042, -80.01085443985608, 1, true, true, 'https://www.visitpittsburgh.com/imager/files_idssasp_com/public/C32/048a7c25-af0f-4edb-9f53-43c4b4f8a7f1/68bf23d3-5c6b-4c74-a888-216f9f34c04d_2200f99a241e55606b0e5da9ea141a39.jpg');

INSERT INTO landmarks (landmark_name, description, hours_of_operation, latitude, longitude, venue_type_id, family_friendly, approved, images)
VALUES                ('The Andy Warhol Museum','the largest museum in North America dedicated to a single artist…. holds an extensive permanent collection of art and archives from the Pittsburgh-born pop art icon Andy Warhol.','M,W-Sat: 10am - 5pm',40.44860398625527,-80.0021367512422,8,true, true, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/The_Andy_Warhol_Museum.jpg/1024px-The_Andy_Warhol_Museum.jpg'),
					  ('Randyland','Folk artist Randy Gilson"s landmark building, filled with vividly colored, upbeat murals & objects.','M-Sun: 1pm - 7pm',40.458191007171614,-80.0091978610234,9, true, true, 'https://www.gannett-cdn.com/authoring/2016/05/29/NETN/ghows-PA-fd471750-4f3d-4a9a-a181-85b7aba4a624-5f7bf508.jpeg?width=1320&height=990&fit=crop&format=pjpg&auto=webp'),
					  ('The Aviary','Discover More Than 500 Birds Of 150 Different Species in Immersive Habitats.','W-Sun: 10am - 5pm',40.45357479723082,-80.00883282662318,6, true, true, 'https://static.wixstatic.com/media/343d41_1f741a004bfc45ac881b10b4b7615a89~mv2.jpg/v1/fill/w_750,h_502,al_c,q_85,enc_auto/343d41_1f741a004bfc45ac881b10b4b7615a89~mv2.jpg'),
					  ('Blue Slide Playground','Blue Slide Playground is part of Frick Park located in Squirrel Hill on Beechwood Blvd.','Open 24hrs, 7 days a week',40.436372195797624,-79.90824360335147,6, true, true, 'http://playpittsburgh.com/wp-content/uploads/2014/06/IMG_3897.jpg'),
					  ('D"s Six Pax and Dogz','Pizza, wings & gourmet dogs smothered in chili & coleslaw, as well as over 1,000 varieties of beer.','Sun-Sun: 11am - 9pm',40.43226317489467,-79.8930936842968,2, true, true,'https://patch.com/img/cdn20/users/22920172/20210929/021824/styles/patch_image/public/ds___29135500607.png?width=1200'),
					  ('Cathy (Cathedral of Learning)','a 42-story skyscraper that serves as the centerpiece of the University of Pittsburgh"s (Pitt) main campus in the Oakland neighborhood…standing at 535 feet (163 m),[6] the 42-story Late Gothic Revival Cathedral is the tallest educational building in the Western Hemisphere and the second-tallest university building (fifth-tallest educationally-purposed building) in the world','Days? hrs?',40.44458593979581,-79.95281248800562,7,true, true, 'https://upload.wikimedia.org/wikipedia/commons/6/6b/Cathedral_of_Learning_stitch_1.jpg'),
					  ('Fuel and Fuddle','House brews meet a selection of upscale pub fare including thin crust pizza with gourmet toppings.','Mon-Sun: 11AM–12AM',40.44135811462849,-79.95577078058707,4,false, true,'https://archive.triblive.com/wp-content/uploads/2018/11/PTRKORNERFUEL3072614.jpg'),
					  ('Girasole','Contemporary, seasonal updates of rustic Italian dishes served in a setting with stone walls.','Tue-Sat:  11:30am -9pm, Sun: 4pm- 9pm',40.45143767661577,-79.93386029964158,2,true, true, 'https://www.discovertheburgh.com/wp-content/uploads/2021/12/Girasole-2000px-1-1024x768.jpg'),
					  ('Monterey Bay Fish Grotto','Pittsburgh"s premier seafood restaurant and boasts stunning 360 views of Pittsburgh"s skyline, high atop Mt. Washington.','Sun-Th: 4:30pm - 9pm, FS: 4:30pm -10pm',40.439059724506436,-80.02034179593272,2,true, true, 'https://1xgkny2ptememg5my1a7sxi5-wpengine.netdna-ssl.com/wp-content/uploads/2019/05/MontereyBayFishGrotto_MontereyBayFishGrotto_Facebook-1024x683.jpg'),
					  ('Frick Park','At 644 acres, Frick Park is Pittsburgh"s largest historic regional park.','MTWThF: 9am - 5pm',40.434007829830506,-79.90372543245007,6,true, true, 'http://www.laquatrabonci.com/images/projects/05_summerset/summerset_09.png'),
					  ('Umi','Traditional Japanese art reflects the ancient art of sushi while lively music and contemporary service align with the modern and masterful preparation of the plates.','T-Th: 5pm - 10pm, FSat: 5pm - 10pm',40.45737455641817,-79.92889445731404,2, true, true, 'https://www.gotodestinations.com/wp-content/uploads/2021/12/Umi.jpg?ezimgfmt=ng:webp/ngcb18'),
					  ('Old Thunder Brewing','Old Thunder Brewing is the apex of experience, creativity, and passion. Brewer-owned & brewer-operated, we are dedicated to serving our diverse community, while remaining loyal to the past, present, and future of beer.','W-Fr: 5pm - 10pm, Sat: 12pm - 10pm, Sun: 12pm - 7pm',40.49387291891346,-79.86005759778527,3, false, true, 'https://media1.fdncms.com/pittsburgh/imager/u/r-bigsquare/20504273/dancinggnome-pittsburgh-teaser.jpg?cb=1636150954'),
					  ('Grist House','From day one, the support of friends and family has been vital to the creation and success of Grist House. This is a place where drinking buddies become lifelong friends, and regulars become family.','T-Fr: 4pm - 10pm, Sat: 12pm - 10pm, Sun: 12pm - 8pm',40.479046382668535,-79.97150531869535,4,false,true,'https://i0.wp.com/newsinteractive.post-gazette.com/distinction/wp-content/uploads/sites/30/2018/02/20180131acGristHouse03-7.jpg?ssl=1'),
					  ('Dancing Gnome','Dancing Gnome was germinated in 2014 with the simple understanding that there was a whole world of unexplored flavor.','T-Fr:4-10pm,Sat:12-8pm,Sun:12p-6pm',40.49406675447894,-79.9308611573127,3,false, true, 'https://breweriesinpa.com/wp-content/uploads/2021/10/IMG_0150.jpg'),
					  ('Hitchhiker','Our original pub (and our former brewery) is located in the heart of Mt. Lebanon and offers both beer and food. It’s an inviting space to hang out for a few beers, or take growlers and cans to go.','TWThF: 4pm - 10pm, Sat: 12pm - 10pm, Sun: 12pm - 8pm',40.50322043242988,-79.90484186040608,4, false,true, 'https://nextpittsburgh.com/wp-content/uploads/2017/09/Hitchhiker1.jpg'),
					  ('Southside trail','Enjoy this 15.4-mile out-and-back trail near Pittsburgh, Pennsylvania. Generally considered an easy route, it takes an average of 5 h 24 min to complete.','MTWThFSS: 6am - 10pm',40.41882541935923,-79.97786454567942,6, true, true, 'https://greatruns.com/wp-content/uploads/2016/11/three-rivers-heritage-trail-1024x768.jpg'),
					  ('Jetski around Point State Park & Station Square','EXPLORE PITTSBURGH ON THE RIVER! Roam freely throughout the entire city on our brand new, top-notch 2021 Yamaha waverunners. Pittsburgh’s one and only jet ski rentals business!','M–W: 12pm – 8pm,Th – Sun: 10am – 8pm',40.4344350693873,-80.004627889860699,9,true, true, 'https://pbs.twimg.com/media/E2JrLrHX0AM0DMR.jpg'),
					  ('Picklesburgh','It"s a culinary celebration that goes beyond the dill pickle to include international dishes, prepared foods and artisan cocktails that feature pickled ingredients; an embrace of the farm-to-table movement and the rising popularity of canning; a selection of handcrafted foods and artisan cocktails from local restaurants; merchandise such as pickled goods, books and DIY products.', 'August 20,21: 12am - 10pm,August 22, 12am - 6pm',40.4456984861663,-80.00359836724763,1,true, true, 'https://goodfoodpittsburgh.com/wp-content/uploads/2021/07/Picklesburgh_Day_Bridge-scaled.jpg'),
					  ('Pamela"s Diner','Charming, retro breakfast & lunch spot for specialty crepe-hotcakes, omelets & burgers.','MTWThFSS: 8am - 2pm',40.45216741769054,-79.98323867822377,2,true, true,'https://media-cdn.tripadvisor.com/media/photo-s/16/ac/7c/82/the-interior.jpg'),
					  ('Primanti Brothers','It all started during the Great Depression - with Joe Primanti operating a small sandwich cart in Pittsburgh"s now-historic Strip District, an area that even in 1933 was a bustling hub of activity.','MTWSS: 11am-12am, ThF: 11am-1am',40.430239848388105,-79.97477373033342,2,true, true,'https://upload.wikimedia.org/wikipedia/commons/1/15/Pittsburgh_Strip_District_Primanti_Bros.jpg'),
					  ('Geppetto Cafe','Geppetto offers an exceptional variety of sweet and savory crepes,...all within a relaxed & charming atmosphere.','M-Fri: 9am - 3pm, Sat&Sun: 9am - 4pm',40.4703171742858,-79.96052066473221,2,true, true,'https://i0.wp.com/endlessbrunch.com/wp-content/uploads/2020/07/IMG_1485.jpg?resize=1024%2C768&ssl=1'),
					  ('Park Bruges','The classic bistro cuisine remains but with an infusion of French fare, hence the French spelling of Bruges.','WTh: Noon - 9pm, F: Noon - 10pm,S: 11am - 10pm,S: 11am - 8pm',40.47639075883511,-79.92018510730972,2,true, true,'https://images.squarespace-cdn.com/content/v1/5b7445a7a9e0284d81334572/1534349667992-4494YXKW7DW8HJSXE6YY/IMG_7305.jpg?format=1500w'),
					  ('Fat Head’s','Casual saloon has 40+ craft beer taps, including house brews, plus burgers, wings & other pub food.','W-Sun:11am-10pm',40.42920385223475,-79.97945124196966,4, true, true, 'https://www.phlf.org/spotlightonmainstreet/buildings/images/large/2314.jpg'),
					  ('Acacia','Sophisticated cocktail bar with a Prohibition theme serving craft drinks in elegant glassware.','M-Th:5pm-12am; F,Sat:5pm - 1am',40.42851075459153,-79.97497095175125,4,false, true,'https://www.pittsburghmagazine.com/content/uploads/data-import/1d64829b/usbgsmall.jpg'),
					  ('Benny Fiori’s','Since 2015 we’ve been slinging 28” pies don here. Open late, BYOB.','Sun-Th:11am-12am; F,Sat:11am-2am',40.428635820226305,-79.9782361168426,2,true, true,'https://thepizzasnob.files.wordpress.com/2016/11/fioris-outside-resize.jpg?w=640'),
					  ('The Summit','Cozy pick offering craft cocktails & elevated pub grub in dimly lit digs with exposed-brick walls.','M-Th:5pm-10pm;F, Sat:5pm-12am;Sun:2pm-7pm',40.43091177973305,-80.00704620335169,4, false, true, 'https://archive.triblive.com/wp-content/uploads/2018/11/PTRTKDINING1030316.jpg'),
					  ('Station','Industrial-chic choice for inventive American fare with an Italian twist, plus craft cocktails.','T-Sat: vary',40.46140247972334,-79.94764583825915,2,true, true,'https://images.squarespace-cdn.com/content/v1/5552557be4b03b3ccda7a921/1496716193415-P62AE7LWLZ9B3X9ZPR5X/IMG_2120-Edit-Edit.jpg?format=2500w'),
					  ('Slice on Broadway','An extensive menu with made-to-order pizzas & house specialties served in a contemporary space.','T-Th:11am-9pm; FS:11am-10pm;Sun:12-8pm',40.4043650366728,-80.02975139964337,2,true, true, 'https://assets-varnish.triblive.com/2020/07/2861677_web1_ptr-SliceOnBroadway4-072920.jpg'),
					  ('Bar Marco','Intimate, minimalist wine bar with a winning menu, vino list, creative cocktails & weekend brunch.','T-Sat:4pm-9pm',40.45267895215274,-79.98102961869637,4,false, true, 'https://media2.fdncms.com/pittsburgh/imager/bar-marco/u/slideshow/1554147/bar_marco_6913.jpg');

COMMIT TRANSACTION;
