"use client";
import React, { useState, useRef, useEffect } from 'react';

type DoubtType = {
  id: string;
  user: string;
  timestamp: string;
  message: string;
  resolved: boolean;
  response?: string;
};

export default function Doubt() {
  const [newDoubt, setNewDoubt] = useState('');
  const [selectedDoubt, setSelectedDoubt] = useState<DoubtType | null>(null);
  const [filter, setFilter] = useState('all');
  const [doubts, setDoubts] = useState<DoubtType[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [response, setResponse] = useState('');
  const responseRef = useRef<HTMLDivElement>(null);

  // Simulated AI responses
  const aiResponses = [
    "I understand your question about authentication. The best approach depends on your specific requirements, but generally you can use JWT tokens with a library like NextAuth.js for React applications. Would you like me to explain the implementation steps?",
    "Streaming data works by establishing a persistent connection between the client and server, allowing data to be sent in chunks as it becomes available rather than waiting for the complete response. This is particularly useful for real-time updates or large data transfers.",
    "To optimize your React application, consider implementing code splitting, memoization with React.memo and useMemo, virtualizing long lists, and using proper state management techniques. Would you like me to elaborate on any specific optimization strategy?",
    "CSS Grid is a two-dimensional layout system that allows you to create complex layouts with rows and columns. Unlike Flexbox, which is primarily one-dimensional, Grid gives you more control over both dimensions simultaneously.",
    "When working with TypeScript in React, it's important to properly type your props, state, and event handlers. You can use interfaces or type aliases to define your component props, and generics for hooks like useState and useRef."
  ];

  // Fetch doubts on component mount
  useEffect(() => {
    // Placeholder data
    setDoubts([
      { 
        id: '1', 
        user: 'User1', 
        timestamp: new Date().toLocaleString(), 
        message: 'How do I implement authentication?', 
        resolved: false,
        response: aiResponses[0]
      },
      { 
        id: '2', 
        user: 'User2', 
        timestamp: new Date().toLocaleString(), 
        message: 'Can you explain how the streaming works?', 
        resolved: true,
        response: aiResponses[1]
      }
    ]);
  }, []);

  // Update selected doubt response when it changes
  useEffect(() => {
    if (selectedDoubt?.response) {
      setResponse(selectedDoubt.response);
    } else {
      setResponse('');
    }
  }, [selectedDoubt]);

  // Simulate AI response with a typewriter effect
  const simulateAIResponse = async (doubtMessage: string) => {
    setIsLoading(true);
    
    // Choose a random response from predefined responses or generate based on the doubt
    let aiResponse = '';
    if (doubtMessage.toLowerCase().includes('typescript')) {
      aiResponse = aiResponses[4];
    } else if (doubtMessage.toLowerCase().includes('css') || doubtMessage.toLowerCase().includes('grid')) {
      aiResponse = aiResponses[3];
    } else if (doubtMessage.toLowerCase().includes('optimize') || doubtMessage.toLowerCase().includes('performance')) {
      aiResponse = aiResponses[2];
    } else {
      // Get a random response if no keyword matches
      const randomIndex = Math.floor(Math.random() * aiResponses.length);
      aiResponse = aiResponses[randomIndex];
    }
    
    // Clear existing response
    setResponse('');
    
    // Simulate streaming response with a typewriter effect
    const chars = aiResponse.split('');
    let currentText = '';
    
    for (let i = 0; i < chars.length; i++) {
      await new Promise(resolve => setTimeout(resolve, 25)); // Adjust speed as needed
      currentText += chars[i];
      setResponse(currentText);
    }
    
    // Add the new doubt to the list with the response
    const newDoubtObj: DoubtType = {
      id: Date.now().toString(),
      user: 'You',
      timestamp: new Date().toLocaleString(),
      message: doubtMessage,
      resolved: false,
      response: aiResponse
    };
    
    setDoubts(prev => [newDoubtObj, ...prev]);
    setSelectedDoubt(newDoubtObj);
    setIsLoading(false);
  };

  const handleAddDoubt = () => {
    if (!newDoubt.trim()) return;
    simulateAIResponse(newDoubt);
    setNewDoubt('');
  };

  const toggleResolved = (id: string) => {
    setDoubts(prev => 
      prev.map(doubt => 
        doubt.id === id ? { ...doubt, resolved: !doubt.resolved } : doubt
      )
    );
    
    if (selectedDoubt && selectedDoubt.id === id) {
      setSelectedDoubt({ ...selectedDoubt, resolved: !selectedDoubt.resolved });
    }
  };

  // Filter doubts based on selection
  const filteredDoubts = doubts.filter(doubt => {
    if (filter === 'all') return true;
    if (filter === 'resolved') return doubt.resolved;
    if (filter === 'unresolved') return !doubt.resolved;
    return true;
  });

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Sidebar with doubt list */}
      <div className="w-1/3 bg-white border-r border-gray-200 flex flex-col">
        <div className="p-4 border-b border-gray-200">
          <h1 className="text-xl font-bold text-gray-800">Doubts</h1>
          <div className="flex mt-2 space-x-2">
            {['all', 'resolved', 'unresolved'].map((type) => (
              <button
                key={type}
                className={`px-3 py-1 rounded-full text-sm ${
                  filter === type
                    ? type === 'resolved'
                      ? 'bg-green-500 text-white'
                      : type === 'unresolved'
                      ? 'bg-yellow-500 text-white'
                      : 'bg-blue-500 text-white'
                    : 'bg-gray-200 text-gray-700'
                }`}
                onClick={() => setFilter(type)}
              >
                {type.charAt(0).toUpperCase() + type.slice(1)}
              </button>
            ))}
          </div>
        </div>
        
        {/* List of doubts */}
        <div className="flex-1 overflow-y-auto">
          {filteredDoubts.length > 0 ? (
            filteredDoubts.map((doubt) => (
              <div
                key={doubt.id}
                className={`p-4 border-b border-gray-200 cursor-pointer hover:bg-gray-50 ${
                  selectedDoubt?.id === doubt.id ? 'bg-blue-50' : ''
                }`}
                onClick={() => setSelectedDoubt(doubt)}
              >
                <div className="flex justify-between items-start">
                  <div>
                    <h3 className="font-medium text-gray-800">{doubt.user}</h3>
                    <p className="text-sm text-gray-500 mt-1">
                      {doubt.timestamp}
                    </p>
                  </div>
                  <span
                    className={`text-xs px-2 py-1 rounded-full ${
                      doubt.resolved
                        ? 'bg-green-100 text-green-800'
                        : 'bg-yellow-100 text-yellow-800'
                    }`}
                  >
                    {doubt.resolved ? 'Resolved' : 'Pending'}
                  </span>
                </div>
                <p className="text-sm text-gray-600 mt-2 line-clamp-2">
                  {doubt.message}
                </p>
              </div>
            ))
          ) : (
            <div className="p-4 text-center text-gray-500">
              No doubts found
            </div>
          )}
        </div>
      </div>

      {/* Main content area */}
      <div className="flex-1 flex flex-col">
        {selectedDoubt ? (
          <>
            <div className="p-4 border-b border-gray-200 bg-white flex justify-between items-center">
              <div>
                <h2 className="text-lg font-semibold text-gray-800">
                  Doubt from {selectedDoubt.user}
                </h2>
                <p className="text-sm text-gray-500">{selectedDoubt.timestamp}</p>
              </div>
              <button
                className={`px-4 py-2 rounded-lg ${
                  selectedDoubt.resolved
                    ? 'bg-gray-500 hover:bg-gray-600'
                    : 'bg-green-500 hover:bg-green-600'
                } text-white`}
                onClick={() => toggleResolved(selectedDoubt.id)}
              >
                {selectedDoubt.resolved ? 'Mark as Pending' : 'Mark as Resolved'}
              </button>
            </div>

            <div className="flex-1 p-6 overflow-y-auto bg-gray-50">
              <div className="bg-white rounded-lg p-4 shadow">
                <p className="text-gray-800">{selectedDoubt.message}</p>
              </div>

              <div className="mt-6">
                <h3 className="text-sm font-medium text-gray-500 mb-2">AI Response</h3>
                <div 
                  ref={responseRef}
                  className="bg-white rounded-lg p-4 shadow"
                >
                  {response ? (
                    <p className="text-gray-800">{response}</p>
                  ) : (
                    <p className="text-sm text-gray-500 italic">No AI response yet.</p>
                  )}
                </div>
              </div>
            </div>
          </>
        ) : (
          <div className="flex-1 flex items-center justify-center bg-gray-50">
            <p className="text-lg text-gray-500">Select a doubt to view details</p>
          </div>
        )}

        <div className="p-4 border-t border-gray-200 bg-white">
          <div className="flex space-x-2">
            <input
              type="text"
              className="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="What's your doubt?"
              value={newDoubt}
              onChange={(e) => setNewDoubt(e.target.value)}
              onKeyPress={(e) => e.key === 'Enter' && !isLoading && handleAddDoubt()}
              disabled={isLoading}
            />
            <button
              className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg disabled:bg-blue-300"
              onClick={handleAddDoubt}
              disabled={isLoading}
            >
              {isLoading ? 'AI is thinking...' : 'Send'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}