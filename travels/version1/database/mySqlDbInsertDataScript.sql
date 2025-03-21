INSERT INTO classifiers(title, description)
VALUES('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_MEDICAL',
    'Travel policy medical risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_CANCELLATION',
    'Travel policy trip cancellation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_LOSS_BAGGAGE',
    'Travel policy baggage lose risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_THIRD_PARTY_LIABILITY',
    'Travel policy third party liability risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_EVACUATION',
    'Travel policy evacuation risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'TRAVEL_SPORT_ACTIVITIES',
    'Travel policy sport activities risk type'
 FROM classifiers as cl
 WHERE cl.title = 'RISK_TYPE';


INSERT INTO classifiers(title, description)
VALUES('COUNTRY', 'Country classifier');

INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'LATVIA',
    'Country Latvia'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'SPAIN',
    'Country Spain'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';

INSERT INTO classifier_values(
	classifier_id,
    ic,
    description)
SELECT
	cl.id,
    'JAPAN',
    'Country Japan'
 FROM classifiers as cl
 WHERE cl.title = 'COUNTRY';


INSERT INTO country_default_day_rate(country_ic, default_day_rate)
VALUES('LATVIA', 1.00),
      ('SPAIN', 2.50),
      ('JAPAN', 3.50);


INSERT INTO age_coefficient(age_from, age_to, coefficient)
VALUES(0, 5, 1.1),
      (6, 10, 0.7),
      (11, 17, 1.0),
      (18, 40, 1.1),
      (41, 65, 1.2),
      (66, 150, 1.5);


INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
VALUES('LEVEL_10000', 1.0),
      ('LEVEL_15000', 1.2),
      ('LEVEL_20000', 1.5),
      ('LEVEL_50000', 2.0);