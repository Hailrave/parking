# parking

Техническое задание:  
Необходимо реализовать сервис, который получает информацию по загрузке парковки.
Реализовать 4 CRUD запроса: получение, создание, редактирование и удаление всех связей данных одним запросом.

Требования:

•	Хранить 3 сущности: 
1) машина (клиент);
2) парковочное место;
3) время бронирования и цена;  

•	Обеспечить связи между сущностями  
•	Хранить данные сущности в бд  
•	Обеспечить получение данных согласно тексту задачи всех связанных сущностей  
•	Получать данные из бд средствами JPA  
•	Реализовать REST API для получения данных  
•	Для проверки рекомендовано использовать Swagger  
  
<br />  

    
Технологический стек:  
1)	Клиентская часть (отсутствует) – для проверки используется Postman
2)	Серверная часть – Spring Boot
3)	JPA - Hibernate
4)	Бд – PostgreSQL
5)	Обеспечена автоматическая накатка данных в бд средствами Flyway
