import {useEffect, useRef} from "react";

/** Hook to poll at interval of "delay" */
export function usePolling(callback: Function, delay: number) {
  const callbackRef = useRef<Function | null>(null);
  
  useEffect(() => {
    callbackRef.current = callback;
  }, [callback]);
  
  useEffect(() => {
    let interval = setInterval(() => {
      if (callbackRef.current)
        callbackRef.current();
    }, delay);
    return () => clearInterval(interval);
  }, [delay]);
}