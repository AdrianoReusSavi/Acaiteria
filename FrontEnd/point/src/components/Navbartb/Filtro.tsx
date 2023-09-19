import Input from "../Inputs/primary-input";

export default function Filtro() {
    return (
    <div className="flex items-center justify-between">
        <div className="flex  space-x-9">
            <h1 className="hover:underline">TODOS</h1>
            <h1 className="hover:underline">AÇAÍ</h1>
            <h1 className="hover:underline">PARÁ</h1>
            <h1 className="hover:underline">BEBIDA</h1>
        </div>
        <div>
            <Input />
        </div>
    </div>)
}