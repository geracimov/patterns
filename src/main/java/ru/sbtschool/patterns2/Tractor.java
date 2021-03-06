package ru.sbtschool.patterns2;

import ru.sbtschool.patterns2.commands.*;
/**
 * Created by Home on 16.08.2018.
 * https://bitbucket.org/agoshkoviv/patterns-homework-1/src/69a61334ea43ff4c3fd950a00095377cf1e3bfd4/src/main/java/ru/sbt/test/refactoring/?at=master
 */
public class Tractor {
    public static Command CMD_FORWARD;
    public static Command CMD_TURN;

    private final Bounds bounds;

    private int[] position = new int[]{ 0, 0 };
    private Orientation orientation = Orientation.NORTH;

    public Tractor( Bounds bounds ) {
        this.bounds = bounds;
        /*вот тут не смог придумать как нужно правильно инициализировать команды :( */
        CMD_FORWARD = new ForwardCommand( this );
        CMD_TURN = new TurnCommand( this );
    }

    public void move( Command command ) {
        command.execute();
    }

    public void moveForwards() {
        position = orientation.move( position[ 0 ], position[ 1 ] );

        if ( !bounds.inBounds( position[ 0 ], position[ 1 ] ) )
            throw new TractorInDitchException();
    }

    public void turnClockwise() {
        orientation = orientation.turn();
    }

    public int getPositionX() {
        return position[ 0 ];
    }

    public int getPositionY() {
        return position[ 1 ];
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
