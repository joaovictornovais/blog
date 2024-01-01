import TabPost from "./TabPost"

const Hero = () => {
  return (
    <main className="h-screen bg-neutral-800 text-zinc-100 pt-[48px]">
        <div className="max-w-6xl mx-auto py-6 px-4">
            <div className="grid grid-cols-1 gap-4">
                <TabPost title="Hello, world!" subtitle="This is my first post!" date="01/01/2023"/>
            </div>
        </div>
    </main>
  )
}

export default Hero