import { FaGithub } from 'react-icons/fa' 

const Header = () => {
  return (
    <header className="fixed w-full bg-neutral-900 p-2">
        <nav className="flex justify-between max-w-6xl mx-auto text-zinc-100 items-center">
            <h2 className="font-medium text-2xl">João Victor</h2>
            <ul className="flex items-center gap-4 font-semibold">
                <a href="https://github.com/joaovictornovais/blog" target="_blank" className="flex items-center gap-2 hover:text-red-700 transition-colors">
                    <FaGithub size={22} />
                    <span className="text-lg">Source</span>
                </a>
            </ul>
        </nav>
    </header>
  )
}

export default Header