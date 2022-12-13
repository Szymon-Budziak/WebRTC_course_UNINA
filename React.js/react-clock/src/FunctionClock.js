import {useEffect, useState} from "react";

const FunctionClock = () => {
    const [date, setDate] = useState(new Date());
    const [timerID, setTimerID] = useState();

    useEffect(() => {
        // Anything in here is fired on component mount
        const _timerID = setInterval(() => tick(), 1000);
        setTimerID(_timerID);

        return () => {
            // Anything in here is fired on component unmount
            clearInterval(timerID);
        }
    }, []);

    const tick = () => {
        setDate(new Date());
    }

    return (
        <div>
            <h1>Hello, world from function!</h1>
            <h2>It is {date.toLocaleTimeString()}.</h2>
        </div>
    )
}
export default FunctionClock;