const TabPost = ({ title, subtitle, date }) => {
  return (
    <div className="flex flex-col gap-2 w-full border-2 p-4 rounded-lg border-zinc-700 hover:text-red-700 transition-colors cursor-pointer">
        <h2 className="font-bold text-2xl">{ title }</h2>
        <div className="space-y-2">
            <p className="text-zinc-300 text-sm font-normal">{ subtitle }</p>
            <span className="text-zinc-400 text-xs">{ date }</span>
        </div>
    </div>
  )
}

export default TabPost