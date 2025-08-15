class SpaceAge {

    double seconds;

    SpaceAge(double seconds) {
        this.seconds = seconds;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return PLANET.Earth.getAge(seconds);
    }

    double onMercury() {
        return PLANET.Mercury.getAge(seconds);
    }

    double onVenus() {
        return PLANET.Venus.getAge(seconds);
    }

    double onMars() {
        return PLANET.Mars.getAge(seconds);
    }

    double onJupiter() {
        return PLANET.Jupiter.getAge(seconds);
    }

    double onSaturn() {
        return PLANET.Saturn.getAge(seconds);
    }

    double onUranus() {
        return PLANET.Uranus.getAge(seconds);
    }

    double onNeptune() {
        return PLANET.Neptune.getAge(seconds);
    }

    enum PLANET {
        Earth(1.0),
        Mercury(0.2408467),
        Venus(0.61519726),
        Mars(1.8808158),
        Jupiter(11.862615),
        Saturn(29.447498),
        Uranus(84.016846),
        Neptune(164.79132);

        private final static int EARTH_ORBITAL_PERIOD = 31557600;

        double orbitalPeriod;
        double calculatedPeriod;

        PLANET(double orbitalPeriod) {
            this.orbitalPeriod = orbitalPeriod;
            this.calculatedPeriod = orbitalPeriod * EARTH_ORBITAL_PERIOD; // 31557600 is secondsInEarthYear;
        }

        public double getAge(double seconds) {
            return seconds / calculatedPeriod;
        }
    }
}
