module zoo.animal.talks {
    exports zoo.animal.talks.content;
    exports zoo.animal.talks.media;
    exports zoo.animal.talks.schedule;

    //requires zoo.animal.feeding;
    requires transitive zoo.animal.care;
}