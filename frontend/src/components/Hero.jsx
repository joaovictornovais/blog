import { useState } from "react";
import TabPost from "./TabPost"
import axios from "axios";
import { useEffect } from "react";

const Hero = () => {

  const [posts, setPosts] = useState([]);

  const loadPosts = () => {
    axios.get('http://localhost:8080/posts').then(data => (
      setPosts(data.data)
    ))
  }

  useEffect(() => {
    loadPosts();
  }, [])

  return (
    <main className="h-screen bg-neutral-800 text-zinc-100 pt-[48px]">
        <div className="max-w-6xl mx-auto py-6 px-4">
            <div className="grid grid-cols-1 gap-4">
                {
                  posts.map((post) => (
                    <TabPost key={post.id} title={post.title} subtitle={post.subtitle} date={post.date}/>
                  ))
                }
            </div>
        </div>
    </main>
  )
}

export default Hero