INSERT INTO Event(id, name, address, city, province, country, start_date, end_date, start_time, end_time, location, artist, description, image) 
VALUES ( nextval ('hibernate_sequence') , 'Not in the Lifetime Tour', 'Cmo. de Perales, 23, 28041', 'MADRID', 'MADRID', 'SPAIN', '2018-08-05', '2018-08-06', '18:00', '23:00', 'Caja Magica', 'Guns n Roses', 'The revived Guns N’ Roses and ...', 'guns-P1080795.jpg' );
INSERT INTO Event(id, name, address, city, province, country, start_date, end_date, start_time, end_time, location, artist, description, image) 
VALUES ( nextval ('hibernate_sequence') , 'CONCRETE AND GOLD TOUR 2018', '8 Boulevard de Bercy, Paris', 'PARIS', 'PARIS', 'FRANCE', '2018-09-15', '2018-09-16', '18:00', '23:00', 'AccorHotels Arena', 'Foo Fighters', 'Concrete and Gold Tour is...', 'foo-P1000628.jpg' );

-- INSERT INTO Image(id, name, photo) 
-- VALUES ( nextval ('hibernate_sequence'), 'foo-P1000628', FILE_READ('classpath:/images/foo-P1000628.jpeg'));
-- INSERT INTO Image(id, name, photo) 
-- VALUES ( nextval ('hibernate_sequence'), 'guns-P1080795', FILE_READ('classpath:/images/guns-P1080795.jpeg'));