import React from "react";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // 1. Token ko remove karein
    localStorage.removeItem("token");
    // 2. Login page par wapas bhej dein
    navigate("/");
  };

  return (
    <div className="relative flex flex-col justify-center items-center min-h-screen bg-gradient-to-br from-gray-900 via-black to-gray-900 overflow-hidden font-sans p-4">
      
      {/* Background Glass Card */}
      <div className="relative z-10 w-full max-w-4xl bg-white/5 backdrop-blur-xl p-10 rounded-3xl border border-white/10 shadow-2xl text-center">
        
        <h1 className="text-4xl md:text-5xl font-black mb-4 text-white tracking-tight bg-gradient-to-r from-blue-400 via-cyan-300 to-purple-400 bg-clip-text text-transparent">
          Welcome to Blooms Dashboard
        </h1>
        
        <p className="text-gray-400 mb-8 text-lg">
          You have successfully logged into your secure account.
        </p>

        {/* Action Buttons */}
        <div className="flex gap-4 justify-center">
          <button className="px-6 py-2 bg-blue-600/20 border border-blue-500/50 text-blue-400 rounded-xl hover:bg-blue-600/30 transition-all">
            View Profile
          </button>
          
          <button 
            onClick={handleLogout}
            className="px-6 py-2 bg-red-600/20 border border-red-500/50 text-red-400 rounded-xl hover:bg-red-600/30 transition-all"
          >
            Logout
          </button>
        </div>
      </div>

      {/* Background Decorative Circles (Matching Login Style) */}
      <div className="absolute top-1/4 left-1/4 w-96 h-96 bg-blue-500/10 rounded-full blur-3xl"></div>
      <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-purple-500/10 rounded-full blur-3xl"></div>
    </div>
  );
};

export default Dashboard;