package com.ldbc.driver.util.temporal;

import java.util.concurrent.TimeUnit;

public class Temporal implements MultipleTimeUnitProvider<Temporal>
{
    public static Temporal fromNano( long ns )
    {
        return new Temporal( ns );
    }

    private final long nanoValue;

    private Temporal( long nanoValue )
    {
        this.nanoValue = nanoValue;
    }

    @Override
    public Long asNano()
    {
        return nanoValue;
    }

    @Override
    public Long asMicro()
    {
        return convert( nanoValue, TimeUnit.NANOSECONDS, TimeUnit.MICROSECONDS );
    }

    @Override
    public Long asMilli()
    {
        return convert( nanoValue, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS );
    }

    @Override
    public Long asSeconds() throws TemporalException
    {
        return convert( nanoValue, TimeUnit.NANOSECONDS, TimeUnit.SECONDS );
    }

    @Override
    public Long as( TimeUnit timeUnit )
    {
        return convert( nanoValue, TimeUnit.NANOSECONDS, timeUnit );
    }

    static long convert( long unitOfTime, TimeUnit timeUnitFrom, TimeUnit timeUnitTo ) throws TemporalException
    {
        long unitOfTimeInNewUnit = timeUnitTo.convert( unitOfTime, timeUnitFrom );
        if ( unitOfTimeInNewUnit == Long.MIN_VALUE || unitOfTimeInNewUnit == Long.MAX_VALUE )
        {
            throw new TemporalException( String.format( "Overflow while converting %s %s to %s", unitOfTime,
                    timeUnitFrom, timeUnitTo ) );
        }
        return unitOfTimeInNewUnit;
    }

    @Override
    public boolean greatThan( Temporal other )
    {
        return this.asNano() > other.asNano();
    }

    @Override
    public boolean lessThan( Temporal other )
    {
        return this.asNano() < other.asNano();
    }

    @Override
    public Duration greaterBy( Temporal other )
    {
        return Duration.fromNano( this.asNano() - other.asNano() );
    }

    @Override
    public Duration lessBy( Temporal other )
    {
        return Duration.fromNano( other.asNano() - this.asNano() );
    }

    @Override
    public Temporal plus( Duration duration )
    {
        return fromNano( this.asNano() + duration.asNano() );
    }

    @Override
    public Temporal minus( Duration duration )
    {
        return fromNano( this.asNano() - duration.asNano() );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( nanoValue ^ ( nanoValue >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Temporal other = (Temporal) obj;
        return this.nanoValue == other.nanoValue;
    }
}