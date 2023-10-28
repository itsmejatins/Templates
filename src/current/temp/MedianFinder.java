package current.temp;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {

		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> a - b < 0 ? 1 : -1);
		int median, size = 0, median2;
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
				if(minHeap.size() == 0) // first number, both queues will be empty
				{
						median = num;
						median2 = num;
						minHeap.add(num);
						maxHeap.add(num);
				}
				else
				{
						if(num >= median)
						{
								minHeap.offer(num);
								if(size % 2 == 0) // only then median changes
								{
										minHeap.poll();
										median = minHeap.peek();
										median2 = median;
										maxHeap.offer(median);
								}
								else
								{
										minHeap.poll();
										median2 = minHeap.peek();
										minHeap.offer(median);
								}
						}
						else
						{
								maxHeap.offer(num);
								if(size % 2 != 0)
								{
										maxHeap.poll();
										median = maxHeap.peek();
										minHeap.offer(median);
								}
								else
								{
										median2 = median;
								}
						}
				}

				size ++;
    }
    
    public double findMedian() {
        return (median + median2) / 2.0;
    }
    
    public static void main(String[] args)
	{
		MedianFinder o = new MedianFinder();
		o.addNum(1);
		o.addNum(2);
		o.addNum(3);
		o.addNum(4);
		
	}
}
